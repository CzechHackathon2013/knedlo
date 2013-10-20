//
//  ImageLoader.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ImageLoader : NSObject

+ (void)loadImageWithURL:(NSURL *)url completionHandler:(void (^)(UIImage *img))handler;

@end
