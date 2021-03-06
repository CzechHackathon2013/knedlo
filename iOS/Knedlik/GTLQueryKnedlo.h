/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2013 Google Inc.
 */

//
//  GTLQueryKnedlo.h
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   knedlo/v1
// Description:
//   This is an API
// Classes:
//   GTLQueryKnedlo (4 custom class methods, 4 custom properties)

#if GTL_BUILT_AS_FRAMEWORK
  #import "GTL/GTLQuery.h"
#else
  #import "GTLQuery.h"
#endif

@interface GTLQueryKnedlo : GTLQuery

//
// Parameters valid on all methods.
//

// Selector specifying which fields to include in a partial response.
@property (copy) NSString *fields;

//
// Method-specific parameters; see the comments below for more information.
//
@property (copy) NSString *action;
@property (copy) NSString *articleLink;
@property (assign) NSInteger page;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQueryKnedlo object.

// Method: knedlo.action
//  Authorization scope(s):
//   kGTLAuthScopeKnedloUserinfoEmail
// Fetches a GTLKnedloBadgeCollection.
+ (id)queryForActionWithAction:(NSString *)action
                   articleLink:(NSString *)articleLink;

// Method: knedlo.badges
//  Authorization scope(s):
//   kGTLAuthScopeKnedloUserinfoEmail
// Fetches a GTLKnedloBadgeCollection.
+ (id)queryForBadges;

#pragma mark -
#pragma mark "endpoint" methods
// These create a GTLQueryKnedlo object.

// Method: knedlo.endpoint.log
//  Authorization scope(s):
//   kGTLAuthScopeKnedloUserinfoEmail
// Fetches a GTLKnedloLogger.
+ (id)queryForEndpointLog;

#pragma mark -
#pragma mark Service level methods
// These create a GTLQueryKnedlo object.

// Method: knedlo.feed
//  Optional:
//   page: NSInteger
//  Authorization scope(s):
//   kGTLAuthScopeKnedloUserinfoEmail
// Fetches a GTLKnedloArticleCollection.
+ (id)queryForFeed;

@end
